import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikaziUniverzitetComponent } from './prikazi-univerzitet.component';

describe('PrikaziUniverzitetComponent', () => {
  let component: PrikaziUniverzitetComponent;
  let fixture: ComponentFixture<PrikaziUniverzitetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrikaziUniverzitetComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrikaziUniverzitetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
