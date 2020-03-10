import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";


const MENU_API = 'menu/';


@Injectable({
  providedIn: 'root'
})
export class SidebarService {



  constructor(private httpClient: HttpClient) {
  }

  public getMenuItems(): Observable<any> {
    return this.httpClient.get<any>(MENU_API + 'menuItems');
  }

  public getSubMenuItems(): Observable<any> {
    return this.httpClient.get<any>(MENU_API + 'subMenuItems');
  }

}
