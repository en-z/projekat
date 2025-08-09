import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajObavjestenjaComponent } from './dodaj-obavjestenja.component';

describe('DodajObavjestenjaComponent', () => {
  let component: DodajObavjestenjaComponent;
  let fixture: ComponentFixture<DodajObavjestenjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodajObavjestenjaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DodajObavjestenjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
