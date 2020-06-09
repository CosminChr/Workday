import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";


const MENU_API = 'menu/menuItems/';

const SUB_MENU_API = 'menu/subMenuItems/';


@Injectable({
  providedIn: 'root'
})
export class SidebarService {


  constructor(private httpClient: HttpClient) {
  }

  public getMenuItems(employeeId: number): Observable<any> {
    return this.httpClient.get<any>(MENU_API + employeeId);
  }

  public getSubMenuItems(): Observable<any> {
    return this.httpClient.get<any>(SUB_MENU_API);
  }

}
