import {Injectable} from "@angular/core";
import {TokenStorageService} from "../../../core/services/security/token-storage.service";
import {some} from 'lodash';
import {Employee} from "../../models/employee.model";
import {EmployeeRoleEnum} from "../../enums/employee-role.enum";


@Injectable({
  providedIn: 'root'
})
export class RoleService {

  roles: [];
  currentEmployee: Employee;

  constructor(private tokenStorageService: TokenStorageService) {
  }

  getCurrentUserRoles() {
    const token = this.tokenStorageService.getToken();
    let roles = [];
    if (token) {
      const currentUser = this.tokenStorageService.getUser();
      roles = currentUser.roles;
    }
    return roles;
  }

  currentUserHasRole(role: string): boolean {
      return some(this.roles, (role) => {
        return role === role;
      });
  }

  hasRoleEmployee(): boolean {
    return this.currentUserHasRole(EmployeeRoleEnum.EMPLOYEE);
  }

  hasRoleManager(): boolean {
    return this.currentUserHasRole(EmployeeRoleEnum.MANAGER);
  }

  hasRoleAdmin(): boolean {
    return this.currentUserHasRole(EmployeeRoleEnum.ADMIN);
  }

}
