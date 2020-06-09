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
import {EmployeeService} from "../../services/employee/employee.service";
import {TokenStorageService} from "../../../core/services/security/token-storage.service";

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

  constructor(private authService: AuthService,
              private notificationService: NotificationService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private sidebarService: SidebarService,
              private tokenStorageService: TokenStorageService) {
  }

  ngOnInit(): void {


    this.employee = this.tokenStorageService.getUser();

      forkJoin([
        this.sidebarService.getMenuItems(this.employee.id),
        this.sidebarService.getSubMenuItems()])
        .subscribe(data => {
          this.menuItems = data[0];
          this.subMenuItems = data[1];
          this.initializePageTitle();
          this.handlePageTitleChanges();
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
      }).name : "Date Personale";
    }
  }

  handlePageTitleChanges() {
    this.router.events.subscribe(() => {
      this.initializePageTitle();
    });
  }
}
