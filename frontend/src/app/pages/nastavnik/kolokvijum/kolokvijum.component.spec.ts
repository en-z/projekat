import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KolokvijumComponent } from './kolokvijum.component';

describe('KolokvijumComponent', () => {
  let component: KolokvijumComponent;
  let fixture: ComponentFixture<KolokvijumComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KolokvijumComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KolokvijumComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
