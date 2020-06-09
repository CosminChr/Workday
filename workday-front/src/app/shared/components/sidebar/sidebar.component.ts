import {AfterViewInit, Component, OnInit} from '@angular/core';
import {SidebarService} from "../../services/sidebar/sidebar.service";
import {MenuItem} from "../../models/menuItem.model";
import {Employee} from "../../models/employee.model";
import {EmployeeService} from "../../services/employee/employee.service";
import {TokenStorageService} from "../../../core/services/security/token-storage.service";

@Component({
  selector: 'workday-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  employee: Employee;

  menuItems: Array<MenuItem>;

  constructor(private sidebarService: SidebarService,
              private employeeService: EmployeeService,
              private tokenStorageService: TokenStorageService) {
  }

  ngOnInit() {
    this.employee = this.tokenStorageService.getUser();

    this.sidebarService.getMenuItems(this.employee.id).subscribe(menuItems => {
      this.menuItems = menuItems as Array<MenuItem>;
    });
  }

}
