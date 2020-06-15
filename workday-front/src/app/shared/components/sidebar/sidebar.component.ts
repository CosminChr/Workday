import {AfterViewInit, Component, OnInit} from '@angular/core';
import {SidebarService} from "../../services/sidebar/sidebar.service";
import {MenuItem} from "../../models/menuItem.model";
import {Employee} from "../../models/employee.model";
import {EmployeeService} from "../../services/employee/employee.service";
import {TokenStorageService} from "../../../core/services/security/token-storage.service";
import {Admin} from "../../models/admin.model";

@Component({
  selector: 'workday-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  employee: Employee;

  admin: Admin;

  menuItems: Array<MenuItem>;

  constructor(private sidebarService: SidebarService,
              private employeeService: EmployeeService,
              private tokenStorageService: TokenStorageService) {
  }

  ngOnInit() {
    if (this.tokenStorageService.getUser().roles.filter(role => role === 'Admin').length > 0) {
      this.admin = this.tokenStorageService.getUser();
      this.sidebarService.getMenuItemsForAdmin(this.admin.id).subscribe(menuItems => {
        this.menuItems = menuItems as Array<MenuItem>;
      });
    } else{
      this.employee = this.tokenStorageService.getUser();
      this.sidebarService.getMenuItemsForEmployee(this.employee.id).subscribe(menuItems => {
        this.menuItems = menuItems as Array<MenuItem>;
      });
    }
  }

}
