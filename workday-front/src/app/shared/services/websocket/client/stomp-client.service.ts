import {Injectable} from "@angular/core";
import * as SockJS from 'sockjs-client';
import {CompatClient, Stomp} from "@stomp/stompjs";

const WEBSOCKET_API = 'http://localhost:8080/websocket-endpoint';

@Injectable({
  providedIn: 'root'
})
export class StompClientService {

  stompClient: CompatClient;

  constructor() {

    this.stompClient = Stomp.over(() => {
      return new SockJS(WEBSOCKET_API);
    });
  }
}
