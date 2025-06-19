import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajNastavnikaComponent } from './dodaj-nastavnika.component';

describe('DodajNastavnikaComponent', () => {
  let component: DodajNastavnikaComponent;
  let fixture: ComponentFixture<DodajNastavnikaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodajNastavnikaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DodajNastavnikaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
