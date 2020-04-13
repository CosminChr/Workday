import {AfterViewInit, Component, OnInit} from '@angular/core';
import {EmployeeService} from "../../shared/services/employee/employee.service";
import {DayOfWeekReferentialService} from "./day-of-week-referential.service";
import {WorkFromHomeService} from "./work-from-home.service";
import {forkJoin} from "rxjs";
import {Employee} from "../../shared/models/employee.model";
import {WorkFromHome} from "../../shared/models/work-from-home.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Referential} from "../../shared/models/referential.model";
import {dateDifference, formatDate, parseDate} from "../../shared/utils/utils";

declare var $: any;

@Component({
  selector: 'workday-work-from-home',
  templateUrl: './work-from-home.component.html',
  styleUrls: ['./work-from-home.component.scss']
})
export class WorkFromHomeComponent implements OnInit, AfterViewInit {

  employee: Employee;

  dayOfWeekReferentials: Array<Referential>;

  workFromHome: WorkFromHome;

  workFromHomeFormGroup: FormGroup;

  initialDayOfWeekDay1: string;

  initialDayOfWeekDay2: string;

  constructor(private employeeService: EmployeeService,
              private dayOfWeekReferentialService: DayOfWeekReferentialService,
              private workFromHomeService: WorkFromHomeService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {

    this.employee = this.employeeService.getSavedEmployee();

    forkJoin([
      this.workFromHomeService.getWorkFromHome(this.employee.id),
      this.dayOfWeekReferentialService.getDayOfWeekReferentials()
    ]).subscribe(data => {
      this.workFromHome = data[0] as WorkFromHome;
      this.dayOfWeekReferentials = data[1] as Array<Referential>;
      this.createWorkfromHomeForm();
      this.initialDayOfWeekDay1 = this.workFromHome.dayOfWeekDay1?.label;
      this.initialDayOfWeekDay2 = this.workFromHome.dayOfWeekDay2?.label;
    });
  }

  ngAfterViewInit(): void {

    this.workFromHomeService.getWorkFromHome(this.employee.id)
      .subscribe((data: WorkFromHome) => {
        setTimeout(function () {
          $('.selectpicker').selectpicker();

          $('#dayOfWeekDay1').selectpicker('val', data.dayOfWeekDay1?.label);
          $('#dayOfWeekDay1').selectpicker('refresh');

          $('#dayOfWeekDay2').selectpicker('val', data.dayOfWeekDay2?.label);
          $('#dayOfWeekDay2').selectpicker('refresh');

        }, 200);
      });

    this.workFromHomeService.getWorkFromHome(this.employee.id).subscribe( (data: WorkFromHome) => {

      if (data.dayOfWeekDay1 && data.potentialDayOfWeekDay1 &&
        data.dayOfWeekDay1.label !== data.potentialDayOfWeekDay1.label) {
        setTimeout(function () {
          $('#dayOfWeekDay1').selectpicker('val', data.dayOfWeekDay1?.label);
          $('#dayOfWeekDay1').prop("disabled", true);
          $('#dayOfWeekDay1').selectpicker('refresh');

        }, 200);
      }

      if (data.dayOfWeekDay2 && data.potentialDayOfWeekDay2 &&
        data.dayOfWeekDay2.label !== data.potentialDayOfWeekDay2.label) {
        setTimeout(function () {
          $('#dayOfWeekDay2').selectpicker('val', data.dayOfWeekDay2?.label);
          $('#dayOfWeekDay2').prop("disabled", true);
          $('#dayOfWeekDay2').selectpicker('refresh');

        }, 200);
      }
    });
  }


  createWorkfromHomeForm(): FormGroup {
    this.workFromHomeFormGroup = this.formBuilder.group({
      'joiningDate': [this.employee.joiningDate ? formatDate(this.employee.joiningDate) : '', [Validators.required]],
      'startDateDay1': [this.workFromHome.startDateDay1 ? formatDate(this.workFromHome.startDateDay1) : '', [Validators.required]],
      'startDateDay2': [this.workFromHome.startDateDay2 ? formatDate(this.workFromHome.startDateDay2) : '', [Validators.required]],
      'dayOfWeekDay1': [this.workFromHome.dayOfWeekDay1?.label, [Validators.required, Validators.maxLength(13)]],
      'dayOfWeekDay2': [this.workFromHome.dayOfWeekDay2?.label, [Validators.required, Validators.minLength(13), Validators.maxLength(13)]]
    });

    if (!this.isStartDateDay1Eligibile() || this.workFromHome?.startDateDay1) {
      this.workFromHomeFormGroup.get('startDateDay1').disable();
      $('#dayOfWeekDay1').selectpicker('refresh');
    }

    if (!this.isStartDateDay1Eligibile()) {
      setTimeout(function () {
        $('#dayOfWeekDay1').prop("disabled", true);
      }, 200);
    }

    if (!this.isStartDateDay2Eligibile() || this.workFromHome?.startDateDay2) {
      this.workFromHomeFormGroup.get('startDateDay2').disable();
      $('#dayOfWeekDay2').selectpicker('refresh');
    }

    if (!this.isStartDateDay2Eligibile()) {
      setTimeout(function () {
        $('#dayOfWeekDay2').prop("disabled", true);
      }, 200);
    }
    return this.workFromHomeFormGroup;
  }

  isStartDateDay1Eligibile(): boolean {
    return Math.floor(dateDifference(this.employee.joiningDate, new Date()) / 30) >= 6;
  }

  isStartDateDay2Eligibile(): boolean {
    return Math.floor(dateDifference(this.employee.joiningDate, new Date()) / 30) >= 12;
  }

  onDayOfWeekDay1Change(event) {
    if (!this.workFromHome.potentialDayOfWeekDay1) {
      this.workFromHome.potentialDayOfWeekDay1 = new Referential();
    }

    this.workFromHome.potentialDayOfWeekDay1.label = this.dayOfWeekReferentials[event.target.selectedIndex - 1].label;
    this.workFromHome.dayOfWeekDay1.label = this.initialDayOfWeekDay1 ? this.initialDayOfWeekDay1 : this.workFromHome.dayOfWeekDay1.label;
  }

  onDayOfWeekDay2Change(event) {

    if (!this.workFromHome.potentialDayOfWeekDay2) {
      this.workFromHome.potentialDayOfWeekDay2 = new Referential();
    }

    this.workFromHome.potentialDayOfWeekDay2.label = this.dayOfWeekReferentials[event.target.selectedIndex].label;
    this.workFromHome.dayOfWeekDay2.label = this.initialDayOfWeekDay2 ? this.initialDayOfWeekDay2 : this.workFromHome.dayOfWeekDay2.label;
  }

  putWorkFromHome() {
    this.workFromHome.startDateDay1 = parseDate(this.workFromHomeFormGroup.controls.startDateDay1.value);

    this.workFromHome.startDateDay2 = this.workFromHomeFormGroup.controls.startDateDay2.value != '' ? parseDate(this.workFromHomeFormGroup.controls.startDateDay2.value): null;
    this.workFromHome.employee = this.employee;
    this.workFromHome.lastInitiationDate = new Date();

    this.workFromHomeService.putWorkFromHome(this.workFromHome).subscribe((data: WorkFromHome) => {
      this.workFromHome = data;


      if (this.workFromHome.dayOfWeekDay1 &&  this.workFromHome.potentialDayOfWeekDay1 &&
        this.workFromHome.dayOfWeekDay1.label !== this.workFromHome.potentialDayOfWeekDay1.label) {
        setTimeout(function () {
          $('#dayOfWeekDay1').selectpicker('val', data.dayOfWeekDay1?.label);
          $('#dayOfWeekDay1').prop("disabled", true);
          $('#dayOfWeekDay1').selectpicker('refresh');
        }, 200);
      }

      if (this.workFromHome.dayOfWeekDay2 &&  this.workFromHome.potentialDayOfWeekDay2 &&
        this.workFromHome.dayOfWeekDay2.label !== this.workFromHome.potentialDayOfWeekDay2.label) {
        setTimeout(function () {
          $('#dayOfWeekDay2').selectpicker('val', data.dayOfWeekDay2?.label);
          $('#dayOfWeekDay2').prop("disabled", true);
          $('#dayOfWeekDay2').selectpicker('refresh');
        }, 200);
      }

    });
  }


}
