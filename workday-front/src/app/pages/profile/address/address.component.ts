import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Referential} from "../../../shared/models/referential.model";
import {Address} from "../../../shared/models/address.model";
import {LocalityReferential} from "../../../shared/models/locality.model";
import {forkJoin} from "rxjs";
import {AddressService} from "./address.service";
import {AddressTypeReferentialService} from "./address-type.referential.service";
import {LocalityReferentialService} from "./locality-referential.service";
import {Employee} from "../../../shared/models/employee.model";
import {EmployeeService} from "../../../shared/services/employee/employee.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {WorkdayValidators} from "../../../shared/validators/workday-validators";
import {NotificationService} from "../../../shared/services/notification/notification.service";

declare var $: any;

@Component({
  selector: 'workday-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.scss']
})
export class AddressComponent implements OnInit, AfterViewInit {

  employee: Employee;

  addressReferentials: Array<Referential>;

  addresses: Array<Address>;

  localities: Array<LocalityReferential>;

  addressFormGroups: Array<FormGroup>;

  newAddress = new Address();

  newAddressForm: FormGroup;

  isDoesAnyAddressExist = true;

  selectedLocality = new Array<string>();


  constructor(private addressService: AddressService,
              private addressTypeReferentialService: AddressTypeReferentialService,
              private localityService: LocalityReferentialService,
              private employeeService: EmployeeService,
              private notificationService: NotificationService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {

    this.employee = this.employeeService.getSavedEmployee();

    forkJoin([
      this.addressTypeReferentialService.getAddressTypeReferentials(),
      this.addressService.getAddresses(this.employee.id),
    ])
      .subscribe(data => {
        this.addressReferentials = data[0];
        this.addresses = data[1];
        this.createAddressForms();
        this.createNewAddressForm();
      });

    this.localityService.getLocalityReferentials().subscribe(data => {
      this.localities = data as Array<LocalityReferential>;
    });
  }

  createAddressForms(): Array<FormGroup> {
    if (this.addresses) {
      this.addressFormGroups = new Array<FormGroup>(this.addresses.length);
      for (let i = 0; i < this.addresses.length; i++) {
        this.addressFormGroups[i] = this.formBuilder.group({
          'addressType': [this.addresses[i].addressType?.label, [Validators.required, Validators.maxLength(100)]],
          'street': [this.addresses[i].street, [Validators.required, Validators.maxLength(100)]],
          'number': [this.addresses[i].number, [Validators.required, Validators.maxLength(100)]],
          'block': [this.addresses[i].block, [Validators.maxLength(100)]],
          'stairwell': [this.addresses[i].stairwell, [Validators.maxLength(100), WorkdayValidators.validNumber]],
          'floor': [this.addresses[i].floor, [Validators.maxLength(100), WorkdayValidators.validNumber]],
          'apartmentNumber': [this.addresses[i].apartmentNumber, [Validators.maxLength(100), WorkdayValidators.validNumber]],
          'locality': [this.addresses[i].locality?.label, [Validators.required, Validators.maxLength(100)]],
          'county': [this.addresses[i].locality?.county?.label, [Validators.required, Validators.maxLength(100)]],
          'country': [this.addresses[i].locality?.country?.label, [Validators.required, Validators.maxLength(100)]],
          'postalCode': [this.addresses[i].postalCode, [Validators.required, Validators.maxLength(6), Validators.minLength(6), WorkdayValidators.validPostalCode]],
        });
      }
    } else {
      this.isDoesAnyAddressExist = false;
    }
    return this.addressFormGroups;
  }

  createNewAddressForm(): FormGroup {

    this.newAddress.addressType = new Referential();
    this.newAddress.locality = new LocalityReferential();
    this.newAddress.locality.county = new Referential();
    this.newAddress.locality.country = new Referential();

    this.newAddressForm = this.formBuilder.group({
      'addressType': [this.newAddress.addressType.label, [Validators.required, Validators.maxLength(100)]],
      'street': [this.newAddress.street, [Validators.required, Validators.maxLength(100)]],
      'number': [this.newAddress.number, [Validators.required, Validators.maxLength(100)]],
      'block': [this.newAddress.block, [Validators.maxLength(100)]],
      'stairwell': [this.newAddress.stairwell, [Validators.maxLength(100), WorkdayValidators.validNumber]],
      'floor': [this.newAddress.floor, [Validators.maxLength(100), WorkdayValidators.validNumber]],
      'apartmentNumber': [this.newAddress.apartmentNumber, [Validators.maxLength(100), WorkdayValidators.validNumber]],
      'locality': [this.newAddress.locality?.label, [Validators.maxLength(100)]],
      'county': [this.newAddress.locality?.county?.label, [Validators.maxLength(100)]],
      'country': [this.newAddress.locality?.country?.label, [Validators.maxLength(100)]],
      'postalCode': [this.newAddress.postalCode, [Validators.required, Validators.maxLength(6), Validators.minLength(6), WorkdayValidators.validPostalCode]],
    });
    return this.newAddressForm;
  }


  ngAfterViewInit(): void {

    setTimeout(() => {
      if (!this.addresses) {
        this.addresses = new Array<Address>();
      }
      for (let i = 0; i < this.addresses.length; i++) {
        if (this.addresses[i].addressType?.label) {
          $('#addressType-' + i).selectpicker();
          $('#addressType-' + i).selectpicker('val', this.addresses[i].addressType?.label);
          $('#addressType-' + i).selectpicker('refresh');
          if ($('#addressType-' + i).val() === '') {
            this.addressFormGroups[i].markAsPending();
          }
        }
      }

      $('#datatable').DataTable({
        lengthChange: false,
        bInfo: false,
        pagingType: "full_numbers",
        lengthMenu: [
          [7],
          [7]
        ],
        responsive: true,
        language: {
          search: "_INPUT_",
          searchPlaceholder: "Caută job",
          paginate: {
            first: "Prima pagină",
            previous: "Înapoi",
            next: "Înainte",
            last: "Ultima pagină"
          }
        }

      });
    }, 700);
  }

  reinitializePicker() {
    $('.selectpicker').selectpicker('refresh');
  }

  selectRow(event: any) {
    this.selectedLocality[0] = event.target.parentNode.cells[0].textContent;
    this.selectedLocality[1] = event.target.parentNode.cells[1].textContent;
    this.selectedLocality[2] = event.target.parentNode.cells[2].textContent;
  }


  isALocalitySelected(): boolean {
    return this.selectedLocality[0] != null;
  }

  setLocality(): string {
    if (this.isALocalitySelected()) {
      this.newAddressForm.controls.locality.setValue(this.selectedLocality[0]);
      return this.selectedLocality[0];
    } else {
      return "Oraș";
    }
  }

  putAddress(index: number) {

    this.addresses[index].locality.label = this.addressFormGroups[index].controls.locality.value;
    this.addresses[index].addressType.label = this.addressFormGroups[index].controls.addressType.value;
    this.addresses[index].street = this.addressFormGroups[index].controls.street.value;
    this.addresses[index].number = this.addressFormGroups[index].controls.number.value;
    this.addresses[index].block = this.addressFormGroups[index].controls.block.value;
    this.addresses[index].stairwell = this.addressFormGroups[index].controls.stairwell.value;
    this.addresses[index].floor = this.addressFormGroups[index].controls.floor.value ? Number(this.newAddressForm.controls.floor.value) : null;
    this.addresses[index].apartmentNumber = this.addressFormGroups[index].controls.apartmentNumber.value ? Number(this.newAddressForm.controls.apartmentNumber.value) : null;

    this.addresses[index].locality.county.label = this.addressFormGroups[index].controls.county.value;
    this.addresses[index].locality.country.label = this.addressFormGroups[index].controls.country.value;
    this.addresses[index].postalCode = this.addressFormGroups[index].controls.postalCode.value;

    this.addressService.putAddress(this.addresses[index]).subscribe(data => {
      this.addressService.getAddresses(this.employee.id).subscribe(data => {
        this.isDoesAnyAddressExist = true;
        this.notificationService.showNotification('top','center', 'success', 'Datele au fost modificate cu succes.');
        this.addresses = data;
        this.createAddressForms();
        setTimeout(() => {
          for (let i = 0; i < this.addresses.length; i++) {
            if (this.addresses[i].addressType?.label) {
              $('#addressType-' + i).selectpicker();
              $('#addressType-' + i).selectpicker('val', this.addresses[i].addressType?.label);
              $('#addressType-' + i).selectpicker('refresh');
              if ($('#addressType-' + i).val() === '') {
                this.addressFormGroups[i].markAsPending();
              }
            }
          }
        }, 100);
      });
    });
  }

  putNewAddress() {
    if (!this.newAddress.addressType) {
      this.newAddress.addressType = new Referential();
    }
    if (!this.newAddress.locality) {
      this.newAddress.locality.label = this.newAddressForm.controls.locality.value;
      this.newAddress.locality.county = new Referential();
      this.newAddress.locality.country = new Referential();
    }

    this.newAddress.addressType.label = this.newAddressForm.controls.addressType.value;
    this.newAddress.street = this.newAddressForm.controls.street.value;
    this.newAddress.number = this.newAddressForm.controls.number.value;
    this.newAddress.block = this.newAddressForm.controls.block.value;
    this.newAddress.stairwell = this.newAddressForm.controls.stairwell.value;
    this.newAddress.floor = this.newAddressForm.controls.floor.value ? Number(this.newAddressForm.controls.floor.value) : null;
    this.newAddress.apartmentNumber = this.newAddressForm.controls.apartmentNumber.value ? Number(this.newAddressForm.controls.apartmentNumber.value) : null;

    this.newAddress.locality.label = this.newAddressForm.controls.locality.value;
    this.newAddress.locality.county.label = this.selectedLocality[1];
    this.newAddress.locality.country.label = this.selectedLocality[2];
    this.newAddress.postalCode = this.newAddressForm.controls.postalCode.value;

    this.newAddress.employee = this.employee;
    this.addressService.putAddress(this.newAddress).subscribe(data => {
      this.isDoesAnyAddressExist = true;
      this.notificationService.showNotification('top','center', 'success', 'Datele au fost salvate cu succes.');
      this.addresses.push(data as Address);
      this.createAddressForms();
      setTimeout(() => {
        for (let i = 0; i < this.addresses.length; i++) {
          if (this.addresses[i].addressType?.label) {
            $('#addressType-' + i).selectpicker();
            $('#addressType-' + i).selectpicker('val', this.addresses[i].addressType?.label);
            $('#addressType-' + i).selectpicker('refresh');
            if ($('#addressType-' + i).val() === '') {
              this.addressFormGroups[i].markAsPending();
            }
          }
        }
      }, 100);
    });
  }
}
