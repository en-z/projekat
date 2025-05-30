import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EvaluacijeComponent } from './evaluacije.component';

describe('EvaluacijeComponent', () => {
  let component: EvaluacijeComponent;
  let fixture: ComponentFixture<EvaluacijeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EvaluacijeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EvaluacijeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
