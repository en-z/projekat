import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajAngazovanjaComponent } from './dodaj-angazovanja.component';

describe('DodajAngazovanjaComponent', () => {
  let component: DodajAngazovanjaComponent;
  let fixture: ComponentFixture<DodajAngazovanjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodajAngazovanjaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DodajAngazovanjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
