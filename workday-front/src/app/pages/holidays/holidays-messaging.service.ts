import {Injectable} from "@angular/core";
import * as SockJS from 'sockjs-client';
import {CompatClient, Stomp} from "@stomp/stompjs";

@Injectable({
  providedIn: 'root'
})
export class HolidaysMessagingService {

  stompClient: CompatClient;

  constructor() {

    this.stompClient = Stomp.over(() => {
      return new SockJS('http://localhost:8080/websocket-endpoint');
    });
    this.stompClient.connect({}, () => {});
  }

  sendHolidayRequest(managerId: number) {
    this.stompClient.send(
      '/app/employee',
      {},
      managerId.toString()
    );
  }
}
