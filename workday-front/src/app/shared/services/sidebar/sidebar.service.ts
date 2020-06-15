import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";


const MENU_API_EMPLOYEE = 'menu/menuItems/employee/';

const MENU_API_ADMIN = 'menu/menuItems/admin/';

const SUB_MENU_API = 'menu/subMenuItems/';


@Injectable({
  providedIn: 'root'
})
export class SidebarService {


  constructor(private httpClient: HttpClient) {
  }

  public getMenuItemsForEmployee(employeeId: number): Observable<any> {
    return this.httpClient.get<any>(MENU_API_EMPLOYEE + employeeId);
  }

  public getMenuItemsForAdmin(adminId: number): Observable<any> {
    return this.httpClient.get<any>(MENU_API_ADMIN + adminId);
  }

  public getSubMenuItems(): Observable<any> {
    return this.httpClient.get<any>(SUB_MENU_API);
  }

}
