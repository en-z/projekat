import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VratiComponent } from './vrati.component';

describe('VratiComponent', () => {
  let component: VratiComponent;
  let fixture: ComponentFixture<VratiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VratiComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VratiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
