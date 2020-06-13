import {Injectable} from "@angular/core";
import * as SockJS from 'sockjs-client';
import {CompatClient, Stomp} from "@stomp/stompjs";
import {StompClientService} from "./client/stomp-client.service";

@Injectable({
  providedIn: 'root'
})
export class WorkFromHomeMessagingService {

  stompClient: CompatClient;

  constructor(private stompClientService: StompClientService) {
    this.stompClient = this.stompClientService.stompClient;
  }

  sendWorkFromHomeRequest(workFromHomeId: number) {
    this.stompClient.send(
      '/app/employee/workFromHome',
      {},
      workFromHomeId.toString()
    );
  }

  handleWorkFromHomeRequest(workFromHomeId: number) {
    this.stompClient.send(
      '/app/manager/workFromHome',
      {},
      workFromHomeId.toString()
    );
  }
}
