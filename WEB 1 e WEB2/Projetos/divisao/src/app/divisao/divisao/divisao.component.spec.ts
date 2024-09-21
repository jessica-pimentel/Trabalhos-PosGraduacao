import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DivisaoComponent } from './divisao.component';

describe('DivisaoComponent', () => {
  let component: DivisaoComponent;
  let fixture: ComponentFixture<DivisaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DivisaoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DivisaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
