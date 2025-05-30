import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormPredmetComponent } from './form-predmet.component';

describe('FormPredmetComponent', () => {
  let component: FormPredmetComponent;
  let fixture: ComponentFixture<FormPredmetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormPredmetComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormPredmetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
