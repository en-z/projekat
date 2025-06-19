import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajZvanjeComponent } from './dodaj-zvanje.component';

describe('DodajZvanjeComponent', () => {
  let component: DodajZvanjeComponent;
  let fixture: ComponentFixture<DodajZvanjeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodajZvanjeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DodajZvanjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
