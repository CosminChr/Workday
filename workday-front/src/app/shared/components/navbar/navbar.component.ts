import {Component, Input, OnInit} from '@angular/core';
import {AuthService} from "../../../core/services/security/auth.service";
import {NotificationService} from "../../services/notification/notification.service";
import {ActivatedRoute, Router} from "@angular/router";
import {SidebarService} from "../../services/sidebar/sidebar.service";
import {MenuItem} from "../../models/menuItem.model";
import {SubMenuItem} from "../../models/subMenuItem.model";
import {forkJoin} from "rxjs";
import {find} from "lodash";
import {Employee} from "../../models/employee.model";
import {TokenStorageService} from "../../../core/services/security/token-storage.service";
import {Referential} from "../../models/referential.model";
import {NavbarService} from "./navbar.service";
import {RoleReferentialService} from "../../services/role/role-referential.service";
import {EmployeeRoleEnum} from "../../enums/employee-role.enum";
import {SubMenuItemEnum} from "../../enums/sub-menu-item.enum";
import {Notification} from "../../models/notification.model";

declare var $: any;

@Component({
  selector: 'workday-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  @Input()
  isConnected: boolean;

  employee: Employee;

  menuItems: Array<MenuItem>;

  subMenuItems: Array<SubMenuItem>;

  pageTitle: string;

  employeeNotifications = new Array<Notification>();

  managerNotifications = new Array<Notification>();

  roleReferentials: Array<Referential>;

  constructor(private authService: AuthService,
              private notificationService: NotificationService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private sidebarService: SidebarService,
              private tokenStorageService: TokenStorageService,
              private navbarService: NavbarService,
              private roleReferentialService: RoleReferentialService) {
  }

  ngOnInit(): void {


    this.employee = this.tokenStorageService.getUser();

    forkJoin([
      this.sidebarService.getMenuItemsForEmployee(this.employee.id),
      this.sidebarService.getSubMenuItems(),
      this.roleReferentialService.getRoleReferentialsForEmployee(this.employee.id),
      this.notificationService.getNotificationsByEmployeeId(this.employee.id)
    ])
      .subscribe(data => {
        this.menuItems = data[0];
        this.subMenuItems = data[1];
        this.roleReferentials = data[2] as Array<Referential>;
        if (this.isManager() && data[3]) {
          this.managerNotifications = data[3] as Array<Notification>;
        } else if (!this.isManager() && data[3]) {
          this.employeeNotifications = data[3] as Array<Notification>
        }
        this.initializePageTitle();
        this.handlePageTitleChanges();
      });

    this.navbarService.getStoredManagerNotifications().asObservable()
      .subscribe( data => {
        this.managerNotifications = data as Array<Notification>;
      });

    this.navbarService.getStoredEmployeeNotifications().asObservable()
      .subscribe( data => {
        this.employeeNotifications = data as Array<Notification>;
      });
  }

  hideLogoutModal() {
    $(".modal-fade").modal("hide");
    $(".modal-backdrop").remove();
  }

  initializePageTitle() {
    if (this.activatedRoute.snapshot.pathFromRoot &&
      this.activatedRoute.snapshot.pathFromRoot
        .map(p => p.children)[1] &&
      this.activatedRoute.snapshot.pathFromRoot
        .map(p => p.children)[1]
        .map(p => p.routeConfig)
        .map(p => p.path)[0] === 'profile') {

      this.pageTitle = find(this.subMenuItems, (subMenuItem: SubMenuItem) => {
        return subMenuItem.path === this.activatedRoute.snapshot.pathFromRoot
          .map(p => p.children)[1]
          .map(p => p.firstChild)
          .map(p => p.children)[0]
          .map(p => p.routeConfig)
          .map(p => p.path)[0]
      }).name;
    } else if (this.activatedRoute.snapshot.pathFromRoot &&
      this.activatedRoute.snapshot.pathFromRoot
        .map(p => p.children)[1]) {

      this.pageTitle = find(this.menuItems, (menuItem: MenuItem) => {
        return menuItem.path.slice(1) === this.activatedRoute.snapshot.pathFromRoot
          .map(p => p.children)[1]
          .map(p => p.routeConfig)
          .map(p => p.path)[0];
      }) ? find(this.menuItems, (menuItem: MenuItem) => {
        return menuItem.path.slice(1) === this.activatedRoute.snapshot.pathFromRoot
          .map(p => p.children)[1]
          .map(p => p.routeConfig)
          .map(p => p.path)[0];
      }).name : SubMenuItemEnum.PERSONAL_DATA;
    }
  }

  handlePageTitleChanges() {
    this.router.events.subscribe(() => {
      this.initializePageTitle();
    });
  }

  isManager(): boolean {
    return this.roleReferentials && this.roleReferentials.filter(role => role.label === EmployeeRoleEnum.MANAGER).length > 0;
  }

  deactivateNotifications(array: Array<Notification>) {
    array.forEach(notification => {
      notification.active = false;
    });
    this.notificationService.putNotifications(array)
      .subscribe();
  }

  activeNotificationsExist(array: Array<Notification>): boolean {
    return array.filter(notification => notification.active === true).length != 0;
  }

  countActiveNotifications(array: Array<Notification>): number {
    return array && array.filter(notification => notification.active === true).length;
  }
}
