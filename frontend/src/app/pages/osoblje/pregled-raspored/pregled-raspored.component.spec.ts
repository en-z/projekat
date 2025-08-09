import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PregledRasporedComponent } from './pregled-raspored.component';

describe('PregledRasporedComponent', () => {
  let component: PregledRasporedComponent;
  let fixture: ComponentFixture<PregledRasporedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PregledRasporedComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PregledRasporedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
