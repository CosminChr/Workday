import {Injectable} from "@angular/core";
import {BehaviorSubject} from "rxjs";
import {Referential} from "../../models/referential.model";
import {Notification} from "../../models/notification.model";


@Injectable({
  providedIn: 'root'
})
export class NavbarService {

  storedEmployeeNotifications = new BehaviorSubject<Array<Notification>>(new Array<Notification>());

  employeeNotifications = new Array<Notification>();

  storedManagerNotifications = new BehaviorSubject<Array<Notification>>(new Array<Notification>());

  managerNotifications = new Array<Notification>();

  constructor() {
  }

  getStoredEmployeeNotifications(): BehaviorSubject<Array<Notification>> {
    return this.storedEmployeeNotifications;
  }

  setEmployeeNotifications(employeeNotifications: Array<Notification>) {
    this.employeeNotifications = employeeNotifications;
    this.storedEmployeeNotifications.next(this.employeeNotifications);
  }

  getStoredManagerNotifications(): BehaviorSubject<Array<Notification>> {
    return this.storedManagerNotifications;
  }

  setManagerNotifications(managerNotifications: Array<Notification>) {
    this.managerNotifications = managerNotifications;
    this.storedManagerNotifications.next(this.managerNotifications);
  }

}
