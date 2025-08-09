import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DokumentiComponent } from './dokumenti.component';

describe('DokumentiComponent', () => {
  let component: DokumentiComponent;
  let fixture: ComponentFixture<DokumentiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DokumentiComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DokumentiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
