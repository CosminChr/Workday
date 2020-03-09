import { Component, OnInit } from '@angular/core';
import {SidebarService} from "../../services/sidebar/sidebar.service";
import {MenuItem} from "../../models/menuItem.model";

@Component({
  selector: 'workday-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  menuItems: Array<MenuItem>;

  constructor(private sidebarService: SidebarService) {}

  ngOnInit() {
    this.sidebarService.getMenuItems().subscribe(menuItems => {
     this.menuItems = menuItems as Array<MenuItem>;
    });
  }
}
