import { TestBed, async } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { WorkdayComponent } from './workday.component';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule
      ],
      declarations: [
        WorkdayComponent
      ],
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(WorkdayComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'workday-front'`, () => {
    const fixture = TestBed.createComponent(WorkdayComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('workday-front');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(WorkdayComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('.content span').textContent).toContain('workday-front app is running!');
  });
});
