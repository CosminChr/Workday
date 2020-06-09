import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HandleRequestsComponent } from './handle-requests.component';

describe('HandleRequestsComponent', () => {
  let component: HandleRequestsComponent;
  let fixture: ComponentFixture<HandleRequestsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HandleRequestsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HandleRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
