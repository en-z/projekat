import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KolokvijumiComponent } from './kolokvijumi.component';

describe('KolokvijumiComponent', () => {
  let component: KolokvijumiComponent;
  let fixture: ComponentFixture<KolokvijumiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KolokvijumiComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KolokvijumiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
