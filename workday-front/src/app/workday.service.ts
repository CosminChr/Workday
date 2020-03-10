import {Injectable} from "@angular/core";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class WorkdayService {

  storedIsConnected = new BehaviorSubject<boolean>(false);

  isConnected: boolean;

  getStoredIsConnected(): BehaviorSubject<boolean> {
    return this.storedIsConnected;
  }

  setIsConnected(isConnected: boolean) {
    this.isConnected = isConnected;
    this.storedIsConnected.next(this.isConnected);
  }

}
