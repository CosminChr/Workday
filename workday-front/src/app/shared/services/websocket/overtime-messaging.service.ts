import {Injectable} from "@angular/core";
import * as SockJS from 'sockjs-client';
import {CompatClient, Stomp} from "@stomp/stompjs";
import {StompClientService} from "./client/stomp-client.service";

@Injectable({
  providedIn: 'root'
})
export class OvertimeMessagingService {

  stompClient: CompatClient;

  constructor(private stompClientService: StompClientService) {
    this.stompClient = this.stompClientService.stompClient;
  }

  sendOvertimeRequest(workFromHomeId: number) {
    this.stompClient.send(
      '/app/employee/overtime',
      {},
      workFromHomeId.toString()
    );
  }

  handleOvertimeRequest(workFromHomeId: number) {
    this.stompClient.send(
      '/app/manager/overtime',
      {},
      workFromHomeId.toString()
    );
  }
}
