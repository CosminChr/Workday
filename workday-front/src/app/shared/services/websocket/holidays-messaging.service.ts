import {Injectable} from "@angular/core";
import * as SockJS from 'sockjs-client';
import {CompatClient, Stomp} from "@stomp/stompjs";
import {StompClientService} from "./client/stomp-client.service";

@Injectable({
  providedIn: 'root'
})
export class HolidaysMessagingService {

  stompClient: CompatClient;

  constructor(private stompClientService: StompClientService) {
    this.stompClient = this.stompClientService.stompClient;
  }

  sendHolidayRequest(managerId: number) {
    this.stompClient.send(
      '/app/employee',
      {},
      managerId.toString()
    );
  }

  handleHolidayRequest(holidayId: number) {
    this.stompClient.send(
      '/app/manager',
      {},
      holidayId.toString()
    );
  }
}
