import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajZavrsniradComponent } from './dodaj-zavrsnirad.component';

describe('DodajZavrsniradComponent', () => {
  let component: DodajZavrsniradComponent;
  let fixture: ComponentFixture<DodajZavrsniradComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodajZavrsniradComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DodajZavrsniradComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
