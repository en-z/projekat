import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajStudentaComponent } from './dodaj-studenta.component';

describe('DodajStudentaComponent', () => {
  let component: DodajStudentaComponent;
  let fixture: ComponentFixture<DodajStudentaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodajStudentaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DodajStudentaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
