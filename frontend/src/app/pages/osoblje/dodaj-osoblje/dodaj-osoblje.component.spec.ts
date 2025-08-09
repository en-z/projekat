import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajOsobljeComponent } from './dodaj-osoblje.component';

describe('DodajOsobljeComponent', () => {
  let component: DodajOsobljeComponent;
  let fixture: ComponentFixture<DodajOsobljeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodajOsobljeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DodajOsobljeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
