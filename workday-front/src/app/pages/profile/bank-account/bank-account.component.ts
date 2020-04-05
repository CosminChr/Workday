import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Employee} from "../../../shared/models/employee.model";
import {Referential} from "../../../shared/models/referential.model";
import {BankAccount} from "../../../shared/models/bank-account.model";
import {EmployeeService} from "../../../shared/services/employee/employee.service";
import {BankAccountService} from "./bank-account.service";
import {CurrencyReferentialService} from "./currency.service";
import {forkJoin} from "rxjs";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {formatDate, parseDate} from "../../../shared/utils/utils";

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


  constructor(private employeeService: EmployeeService,
              private bankAccountService: BankAccountService,
              private currencyReferentialService: CurrencyReferentialService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {

    this.employee = this.employeeService.getSavedEmployee();

    forkJoin([
      this.currencyReferentialService.getCurrencyReferentials(),
      this.bankAccountService.getBankAccounts(this.employee.id)
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
    setTimeout(function () {
      $('.selectpicker').selectpicker();
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
          'IBAN': [this.bankAccounts[i].iban, [Validators.required, Validators.maxLength(100)]],
          'expirationDate': [this.bankAccounts[i].expirationDate ? formatDate(this.bankAccounts[i].expirationDate) : '', [Validators.required]],
          'currency': [this.bankAccounts[i].currency?.label, [Validators.required, Validators.maxLength(100)]],
          'primaryAccount': [this.bankAccounts[i].primaryAccount, [Validators.required, Validators.maxLength(100)]],
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
      'IBAN': [this.newBankAccount.iban, [Validators.required, Validators.maxLength(100)]],
      'expirationDate': [this.newBankAccount.expirationDate ? formatDate(this.newBankAccount.expirationDate) : '', [Validators.required]],
      'currency': [this.newBankAccount.currency?.label, [Validators.required, Validators.maxLength(100)]],
      'primaryAccount': [this.newBankAccount.primaryAccount, [Validators.required, Validators.maxLength(100)]],
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

  putNewBankAccount() {
    this.newBankAccount.bank = this.bankAccountFormGroup.controls.bank.value;
    this.newBankAccount.agency = this.bankAccountFormGroup.controls.agency.value;
    this.newBankAccount.iban = this.bankAccountFormGroup.controls.IBAN.value;
    this.newBankAccount.expirationDate = parseDate(this.bankAccountFormGroup.controls.expirationDate.value);
    this.newBankAccount.currency.label = this.bankAccountFormGroup.controls.currency.value;
    this.newBankAccount.primaryAccount = this.bankAccountFormGroup.controls.primaryAccount.value;

    this.newBankAccount.employee = this.employee;
    this.bankAccountService.putBankAccount(this.newBankAccount).subscribe(data => {
      this.bankAccounts.push(data as BankAccount);
    });

  }

  reinitializePicker() {
    $('.selectpicker').selectpicker('refresh');
  }
}
