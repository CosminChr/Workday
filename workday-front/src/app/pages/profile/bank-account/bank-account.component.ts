import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Employee} from "../../../shared/models/employee.model";
import {Referential} from "../../../shared/models/referential.model";
import {BankAccount} from "../../../shared/models/bank-account.model";
import {EmployeeService} from "../../../shared/services/employee/employee.service";
import {BankAccountService} from "./bank-account.service";
import {CurrencyReferentialService} from "./currency-referential.service";
import {forkJoin} from "rxjs";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {formatDate, parseDate} from "../../../shared/utils/utils";
import {WorkdayValidators} from "../../../shared/validators/workday-validators";
import {NotificationService} from "../../../shared/services/notification/notification.service";

declare var $: any;

@Component({
  selector: 'workday-bank-account',
  templateUrl: './bank-account.component.html',
  styleUrls: ['./bank-account.component.scss']
})
export class BankAccountComponent implements OnInit, AfterViewInit {

  employee: Employee;

  currencyReferentials: Array<Referential>;

  bankAccounts: Array<BankAccount>;

  newBankAccount = new BankAccount();

  isDoesAnyBankAccountExist = true;

  bankAccountsFormGroups: Array<FormGroup>;

  bankAccountFormGroup: FormGroup;

  bankStatement: any;


  constructor(private employeeService: EmployeeService,
              private bankAccountService: BankAccountService,
              private currencyReferentialService: CurrencyReferentialService,
              private notificationService: NotificationService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.employee = this.employeeService.getSavedEmployee();
    forkJoin([
      this.currencyReferentialService.getCurrencyReferentials(),
      this.bankAccountService.getBankAccounts(this.employeeService.getSavedEmployee().id)
    ])
      .subscribe(data => {
        this.currencyReferentials = data[0];
        this.bankAccounts = data[1];
        this.createBankAccountForms();
        this.populateBankAccountForms();
        this.createNewBankAccountForm();
      });
  }


  ngAfterViewInit(): void {
    setTimeout(() => {

      for (let i = 0; i < this.bankAccounts.length; i++) {
        if (this.bankAccounts[i].currency?.label) {
          $('#currency-' + i).selectpicker();
          $('#currency-' + i).selectpicker('val', this.bankAccounts[i].currency?.label);
          $('#currency-' + i).selectpicker('refresh');
          if ($('#currency-' + i).val() === '') {
            this.bankAccountsFormGroups[i].markAsPending();
          }
        }
      }
    }, 500);
  }

  formatExpirationDate(date: Date): string {
    return new Date(date).getMonth() < 10 ? '0'
      + (new Date(date).getMonth() + 1) + '/' + new Date(date).getFullYear().toString().substring(2)
      : new Date(date).getMonth() + '/' + new Date(date).getFullYear().toString().substring(2);
  }

  createBankAccountForms(): Array<FormGroup> {
    if (this.bankAccounts) {
      this.bankAccountsFormGroups = new Array<FormGroup>(this.bankAccounts.length);
      for (let i = 0; i < this.bankAccounts.length; i++) {
        this.bankAccountsFormGroups[i] = this.formBuilder.group({
          'bank': [this.bankAccounts[i].bank, [Validators.required, Validators.maxLength(100)]],
          'agency': [this.bankAccounts[i].agency, [Validators.required, Validators.maxLength(100)]],
          'IBAN': [this.bankAccounts[i].iban, [Validators.required, Validators.maxLength(24), Validators.minLength(24), WorkdayValidators.validIBAN]],
          'expirationDate': [this.bankAccounts[i].expirationDate ? formatDate(this.bankAccounts[i].expirationDate) : '', [Validators.required, WorkdayValidators.validDate]],
          'currency': [this.bankAccounts[i].currency?.label, [Validators.required, Validators.maxLength(100)]],
          'primaryAccount': [this.bankAccounts[i].primaryAccount],
        });
      }
    } else {
      this.isDoesAnyBankAccountExist = false;
    }
    return this.bankAccountsFormGroups;
  }

  createNewBankAccountForm(): FormGroup {
    this.newBankAccount.currency = new Referential();

    this.bankAccountFormGroup = this.formBuilder.group({
      'bank': [this.newBankAccount.bank, [Validators.required, Validators.maxLength(100)]],
      'agency': [this.newBankAccount.agency, [Validators.required, Validators.maxLength(100)]],
      'IBAN': [this.newBankAccount.iban, [Validators.required, Validators.maxLength(24), Validators.minLength(24), WorkdayValidators.validIBAN]],
      'expirationDate': [this.newBankAccount.expirationDate ? formatDate(this.newBankAccount.expirationDate) : '', [Validators.required, WorkdayValidators.validDate]],
      'currency': [this.newBankAccount.currency?.label, [Validators.required, Validators.maxLength(100)]],
      'primaryAccount': [this.newBankAccount.primaryAccount],
    });

    return this.bankAccountFormGroup;
  }

  populateBankAccountForms(): Array<FormGroup> {

    if (this.bankAccounts) {
      for (let i = 0; i < this.bankAccounts.length; i++) {
        if (!this.bankAccounts[i].primaryAccount) {
          this.bankAccounts[i].primaryAccount = false;
          this.bankAccountsFormGroups[i].controls.primaryAccount.setValue(this.bankAccounts[i].primaryAccount);
          this.bankAccountsFormGroups[i].controls.primaryAccount.updateValueAndValidity();
        }
      }
    }
    return this.bankAccountsFormGroups;
  }

  putBankAccount(index: number) {
    this.bankAccounts[index].bank = this.bankAccountsFormGroups[index].controls.bank.value;
    this.bankAccounts[index].agency = this.bankAccountsFormGroups[index].controls.agency.value;
    this.bankAccounts[index].iban = this.bankAccountsFormGroups[index].controls.IBAN.value;
    this.bankAccounts[index].expirationDate = parseDate(this.bankAccountsFormGroups[index].controls.expirationDate.value);
    this.bankAccounts[index].currency.label = this.bankAccountsFormGroups[index].controls.currency.value;
    this.bankAccounts[index].primaryAccount = this.bankAccountsFormGroups[index].controls.primaryAccount.value;

    const data = new FormData();
    data.append("bankStatement", this.bankStatement ? this.bankStatement : new Blob(), this.bankStatement?.name);
    data.append('bankAccount', new Blob([JSON.stringify(this.bankAccounts[index])], {
      type: "application/json"
    }));

    this.bankAccountService.putBankAccount(data).subscribe(data => {
      this.bankAccountService.getBankAccounts(this.employee.id).subscribe(data => {
        this.bankAccounts = data;
        this.notificationService.showNotification('top', 'center', 'success', 'Datele au fost modificate cu succes.');
        this.createBankAccountForms();
        this.populateBankAccountForms();
        setTimeout(() => {

          for (let i = 0; i < this.bankAccounts.length; i++) {
            if (this.bankAccounts[i].currency?.label) {
              $('#currency-' + i).selectpicker();
              $('#currency-' + i).selectpicker('val', this.bankAccounts[i].currency?.label);
              $('#currency-' + i).selectpicker('refresh');
              if ($('#currency-' + i).val() === '') {
                this.bankAccountsFormGroups[i].markAsPending();
              }
            }
          }
        }, 500);
      });
    });
  }

  putNewBankAccount() {
    this.newBankAccount.bank = this.bankAccountFormGroup.controls.bank.value;
    this.newBankAccount.agency = this.bankAccountFormGroup.controls.agency.value;
    this.newBankAccount.iban = this.bankAccountFormGroup.controls.IBAN.value;
    this.newBankAccount.expirationDate = parseDate(this.bankAccountFormGroup.controls.expirationDate.value);
    this.newBankAccount.currency.label = this.bankAccountFormGroup.controls.currency.value;
    this.newBankAccount.primaryAccount = this.bankAccountFormGroup.controls.primaryAccount.value;
    this.newBankAccount.employee = this.employee;

    const data = new FormData();
    data.append("bankStatement", this.bankStatement ? this.bankStatement : new Blob(), this.bankStatement?.name);
    data.append('bankAccount', new Blob([JSON.stringify(this.newBankAccount)], {
      type: "application/json"
    }));

    this.bankAccountService.putBankAccount(data).subscribe(data => {
      this.bankAccounts.push(data as BankAccount);
      this.notificationService.showNotification('top', 'center', 'success', 'Datele au fost salvate cu succes.');
      this.createBankAccountForms();
      this.populateBankAccountForms();
      setTimeout(() => {

        for (let i = 0; i < this.bankAccounts.length; i++) {
          if (this.bankAccounts[i].currency?.label) {
            $('#currency-' + i).selectpicker();
            $('#currency-' + i).selectpicker('val', this.bankAccounts[i].currency?.label);
            $('#currency-' + i).selectpicker('refresh');
            if ($('#currency-' + i).val() === '') {
              this.bankAccountsFormGroups[i].markAsPending();
            }
          }
        }
      }, 500);
    });

  }

  reinitializePicker() {
    $('.selectpicker').selectpicker('refresh');
  }

  uploadFile(event) {
    this.bankStatement = event.target.files[0];
  }
}
