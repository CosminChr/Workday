import {Component, Input, OnInit} from '@angular/core';
import {AuthService} from "../../../core/services/security/auth.service";
import {NotificationService} from "../../services/notification/notification.service";

declare var $: any;

@Component({
  selector: 'workday-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  @Input()
  isConnected: boolean;

  constructor(private authService: AuthService,
              private notificationService: NotificationService) { }

  ngOnInit(): void {
  }

  hideLogoutModal() {
    $(".modal-fade").modal("hide");
    $(".modal-backdrop").remove();
  }

  showNotification() {
    this.notificationService.showNotification('top','center', 'success', 'Mulțumim că ai folosit aplicația WorkDay !');
  }


}
