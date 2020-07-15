import {AfterViewInit, Component, OnInit} from '@angular/core';
import {SidebarService} from "../../services/sidebar/sidebar.service";
import {MenuItem} from "../../models/menuItem.model";
import {Employee} from "../../models/employee.model";
import {EmployeeService} from "../../services/employee/employee.service";
import {TokenStorageService} from "../../../core/services/security/token-storage.service";
import {Admin} from "../../models/admin.model";
import {EmployeeRoleEnum} from "../../enums/employee-role.enum";
import {RoleReferentialService} from "../../services/role/role-referential.service";
import {Referential} from "../../models/referential.model";

@Component({
  selector: 'workday-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  employee: Employee;

  admin: Admin;

  menuItems: Array<MenuItem>;

  roleReferentials: Array<Referential>;

  constructor(private sidebarService: SidebarService,
              private employeeService: EmployeeService,
              private tokenStorageService: TokenStorageService,
              private roleReferentialService: RoleReferentialService) {
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
        this.roleReferentialService.getRoleReferentialsForEmployee(this.employee.id).subscribe( data => {
          this.roleReferentials = data as Array<Referential>;
        })
      });
    }
  }

  isManager(): boolean {
    return this.roleReferentials && this.roleReferentials.filter(role => role.label === EmployeeRoleEnum.MANAGER).length > 0;
  }

}
