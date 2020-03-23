import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'workday-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.scss']
})
export class AddressComponent implements OnInit {

  copilescu = 'copilescu';
  copchi =  [1,2];

  constructor() { }

  ngOnInit() {
  }

}
