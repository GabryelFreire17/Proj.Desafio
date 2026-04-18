import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComercioList } from './components/comercio-list/comercio-list'; 

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, ComercioList], 
  template: '<app-comercio-list></app-comercio-list>' 
})
export class AppComponent { 
 
}
