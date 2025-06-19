import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IzdatePutComponent } from './izdate-put.component';

describe('IzdatePutComponent', () => {
  let component: IzdatePutComponent;
  let fixture: ComponentFixture<IzdatePutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IzdatePutComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IzdatePutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
