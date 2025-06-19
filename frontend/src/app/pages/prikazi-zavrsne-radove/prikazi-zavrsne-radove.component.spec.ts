import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikaziZavrsneRadoveComponent } from './prikazi-zavrsne-radove.component';

describe('PrikaziZavrsneRadoveComponent', () => {
  let component: PrikaziZavrsneRadoveComponent;
  let fixture: ComponentFixture<PrikaziZavrsneRadoveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrikaziZavrsneRadoveComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrikaziZavrsneRadoveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
