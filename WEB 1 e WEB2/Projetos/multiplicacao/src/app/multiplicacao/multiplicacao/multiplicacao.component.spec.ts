import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MultiplicacaoComponent } from './multiplicacao.component';

describe('MultiplicacaoComponent', () => {
  let component: MultiplicacaoComponent;
  let fixture: ComponentFixture<MultiplicacaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MultiplicacaoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MultiplicacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
