import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { CidadeService } from '../../services/cidade.service';

@Component({
  selector: 'app-comercio-list',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    TableModule,
    ButtonModule,
    DialogModule,
    InputTextModule,
    DropdownModule
  ],
  templateUrl: './comercio-list.html',
  styleUrl: './comercio-list.css'
})
export class ComercioList implements OnInit {
  comercios: any[] = [];
  cidades: any[] = [];
  exibirModal: boolean = false;

  tipos = [
    { label: 'Padaria', value: 'PADARIA' },
    { label: 'Farmácia', value: 'FARMACIA' },
    { label: 'Posto', value: 'POSTO_GASOLINA' },
    { label: 'Lanchonete', value: 'LANCHONETE' }
  ];

  novoComercio: any = { 
    id: null, 
    nome: '', 
    nomeResponsavel: '', 
    tipoComercio: '', 
    cidadeId: null 
  };

  constructor(
    private http: HttpClient, 
    private cidadeService: CidadeService,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.listarTudo();
  }

  listarTudo() {
    this.cidadeService.listar().subscribe({
      next: (dados) => {
        this.cidades = dados;
        this.cd.detectChanges();
      },
      error: (err) => console.error('Erro ao carregar cidades', err)
    });

    this.http.get<any[]>('http://localhost:8080/api/comercio').subscribe({
      next: (dados) => {
        this.comercios = dados;
        this.cd.detectChanges();
      },
      error: (err) => console.error('Erro ao carregar comércios', err)
    });
  }

  abrirModal() {
    this.novoComercio = { id: null, nome: '', nomeResponsavel: '', tipoComercio: '', cidadeId: null };
    this.exibirModal = true;
  }

  editar(comercio: any) {
    this.novoComercio = { 
      id: comercio.id, 
      nome: comercio.nome, 
      nomeResponsavel: comercio.nomeResponsavel, 
      tipoComercio: comercio.tipoComercio,
      cidadeId: comercio.cidadeId 
    };
    this.exibirModal = true;
  }

  salvar() {
    
    const corpoRequisicao = {
      id: this.novoComercio.id,
      nome: this.novoComercio.nome,
      nomeResponsavel: this.novoComercio.nomeResponsavel, 
      tipoComercio: this.novoComercio.tipoComercio,
      cidadeId: this.novoComercio.cidadeId
    };

    if (corpoRequisicao.id === null) {
      delete corpoRequisicao.id;
    }

    
    if (!corpoRequisicao.nomeResponsavel || !corpoRequisicao.cidadeId) {
      alert('Preencha o responsável e a cidade!');
      return;
    }

    this.http.post('http://localhost:8080/api/comercio', corpoRequisicao).subscribe({
      next: () => {
        alert('Salvo com sucesso!');
        this.exibirModal = false;
        this.listarTudo();
      },
      error: (err) => {
        console.error('Erro detalhado:', err);
        alert('Nome ou tipo de comércio já existe nessa cidade');
      }
    });
  }

  excluirComercio(id: number) {
    if (confirm('Deseja excluir este comércio?')) {
      this.http.delete(`http://localhost:8080/api/comercio/${id}`).subscribe(() => this.listarTudo());
    }
  }

  abrirModalCidade() {
    const nome = prompt('Digite o nome da nova cidade:');
    if (nome) {
      this.cidadeService.salvar({ nome }).subscribe(() => this.listarTudo());
    }
  }

  editarCidade(cidade: any) {
    const novoNome = prompt('Editar nome da cidade:', cidade.nome);
    if (novoNome) {
      this.cidadeService.salvar({ id: cidade.id, nome: novoNome }).subscribe(() => this.listarTudo());
    }
  }

  excluirCidade(id: number) {
  if (confirm('Atenção: Isso excluirá a cidade e todos os seus comércios!')) {
    this.cidadeService.excluir(id).subscribe(() => {
      this.listarTudo(); 
    });
  }
}
}
