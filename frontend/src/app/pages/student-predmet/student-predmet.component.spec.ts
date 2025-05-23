import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentPredmetComponent } from './student-predmet.component';

describe('StudentPredmetComponent', () => {
  let component: StudentPredmetComponent;
  let fixture: ComponentFixture<StudentPredmetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudentPredmetComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudentPredmetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
