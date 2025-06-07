import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikazRokovaComponent } from './prikaz-rokova.component';

describe('PrikazRokovaComponent', () => {
  let component: PrikazRokovaComponent;
  let fixture: ComponentFixture<PrikazRokovaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrikazRokovaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrikazRokovaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
