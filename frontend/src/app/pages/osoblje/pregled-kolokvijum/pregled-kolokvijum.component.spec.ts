import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PregledKolokvijumComponent } from './pregled-kolokvijum.component';

describe('PregledKolokvijumComponent', () => {
  let component: PregledKolokvijumComponent;
  let fixture: ComponentFixture<PregledKolokvijumComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PregledKolokvijumComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PregledKolokvijumComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
