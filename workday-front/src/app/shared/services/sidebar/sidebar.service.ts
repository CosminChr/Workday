import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";


const MENU_API = 'http://localhost:8080/api/menu/';



@Injectable({
  providedIn: 'root'
})
export class SidebarService {


    headers = new HttpHeaders({ 'Content-Type': 'application/json' ,
      'Authorization': 'Bearer ' + localStorage.getItem('auth-token')});


  constructor(private httpClient: HttpClient) {
  }

  public getMenuItems(): Observable<any> {
    return this.httpClient.get<any>(MENU_API + 'menuItems', {headers: this.headers});
  }

  public getSubMenuItems(): Observable<any> {
    return this.httpClient.get<any>(MENU_API + 'subMenuItems', {headers: this.headers});
  }

}
