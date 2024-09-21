import { TestBed } from '@angular/core/testing';

import { MultiplicacaoService } from './multiplicacao.service';

describe('MultiplicacaoService', () => {
  let service: MultiplicacaoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MultiplicacaoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
