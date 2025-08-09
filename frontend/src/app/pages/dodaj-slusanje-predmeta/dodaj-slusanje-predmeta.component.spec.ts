import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajSlusanjePredmetaComponent } from './dodaj-slusanje-predmeta.component';

describe('DodajSlusanjePredmetaComponent', () => {
  let component: DodajSlusanjePredmetaComponent;
  let fixture: ComponentFixture<DodajSlusanjePredmetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodajSlusanjePredmetaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DodajSlusanjePredmetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
