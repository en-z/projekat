import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IshodIspitaComponent } from './ishod-ispita.component';

describe('IshodIspitaComponent', () => {
  let component: IshodIspitaComponent;
  let fixture: ComponentFixture<IshodIspitaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IshodIspitaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IshodIspitaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
