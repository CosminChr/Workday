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
          'block': [this.addresses[i].block, [Validators.required, Validators.maxLength(100)]],
          'stairwell': [this.addresses[i].stairwell, [Validators.required, Validators.maxLength(100)]],
          'floor': [this.addresses[i].floor, [Validators.required, Validators.maxLength(100)]],
          'apartmentNumber': [this.addresses[i].apartmentNumber, [Validators.required, Validators.maxLength(100)]],
          'locality': [this.addresses[i].locality?.label, [Validators.required, Validators.maxLength(100)]],
          'county': [this.addresses[i].locality?.county?.label, [Validators.required, Validators.maxLength(100)]],
          'country': [this.addresses[i].locality?.country?.label, [Validators.required, Validators.maxLength(100)]],
          'postalCode': [this.addresses[i].postalCode, [Validators.required, Validators.maxLength(100)]],
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
      'block': [this.newAddress.block, [Validators.required, Validators.maxLength(100)]],
      'stairwell': [this.newAddress.stairwell, [Validators.required, Validators.maxLength(100)]],
      'floor': [this.newAddress.floor, [Validators.required, Validators.maxLength(100)]],
      'apartmentNumber': [this.newAddress.apartmentNumber, [Validators.required, Validators.maxLength(100)]],
      'locality': [this.newAddress.locality?.label, [Validators.required, Validators.maxLength(100)]],
      'county': [this.newAddress.locality?.county?.label, [Validators.required, Validators.maxLength(100)]],
      'country': [this.newAddress.locality?.country?.label, [Validators.required, Validators.maxLength(100)]],
      'postalCode': [this.newAddress.postalCode, [Validators.required, Validators.maxLength(100)]],
    });
    return this.newAddressForm;
  }

  ngAfterViewInit(): void {



    $('.selectpicker').selectpicker();

    setTimeout(function () {
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
          searchPlaceholder: "Caută oraș",
          paginate: {
            first: "Prima pagină",
            previous: "Înapoi",
            next: "Înainte",
            last: "Ultima pagină"
          }
        }

      });
    }, 500);
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
      return this.selectedLocality[0];
    } else {
      return "Oraș";
    }
  }

  putNewAddress() {
    if (!this.newAddress.addressType) {
      this.newAddress.addressType = new Referential();
    }
    if (!this.newAddress.locality) {
      this.newAddress.locality = new LocalityReferential();
      this.newAddress.locality.county = new Referential();
      this.newAddress.locality.country = new Referential();
    }

    this.newAddress.addressType.label = this.newAddressForm.controls.addressType.value;
    this.newAddress.street = this.newAddressForm.controls.street.value;
    this.newAddress.number = this.newAddressForm.controls.number.value;
    this.newAddress.block = this.newAddressForm.controls.block.value;
    this.newAddress.stairwell = this.newAddressForm.controls.stairwell.value;
    this.newAddress.floor = Number(this.newAddressForm.controls.floor.value);
    this.newAddress.apartmentNumber = Number(this.newAddressForm.controls.apartmentNumber.value);
    this.newAddress.locality.label = this.newAddressForm.controls.locality.value;
    this.newAddress.locality.county.label = this.selectedLocality[1];
    this.newAddress.locality.country.label = this.selectedLocality[2];
    this.newAddress.postalCode = this.newAddressForm.controls.postalCode.value;

    this.newAddress.employee = this.employee;
    this.addressService.putAddress(this.newAddress).subscribe( data => {
       this.addresses.push(data as Address);
    });
  }
}
