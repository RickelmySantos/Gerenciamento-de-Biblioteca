import { ChangeDetectionStrategy, Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BaseComponent } from 'src/app/core/util/base.component';
import { SharedModule } from 'src/app/shared/shared.module';

@Component({
    selector: 'app-livros',
    template: `
        <div class=" fluid-content border-1 bg-white">
            <section class="border-1">
                <h1 class="text-2xl">Recomendados</h1>
            </section>
            <section>teste2</section>
        </div>
    `,
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    imports: [SharedModule],
})
export class LivrosComponent extends BaseComponent {
    recommendedBooks = [
        {
            title: 'The Psychology of Money',
            author: 'Morgan Housel',
            image: 'assets/images/psychology_of_money.jpg',
            pages: 320,
            rating: 4.8,
            description: 'A fantastic book on financial thinking.',
        },
        // Outros livros...
    ];

    categories = ['All', 'Sci-Fi', 'Fantasy', 'Drama', 'Business', 'Education', 'Geography'];

    selectedCategory = 'All';

    books = [
        { title: 'The Bees', author: 'Laline Paull', image: 'link-to-image', category: 'Sci-Fi' },
        { title: 'The Fact of a Body', author: 'Alexandria Marzano', image: 'link-to-image', category: 'Drama' },
        // Outros livros
    ];

    filteredBooks = this.books;

    selectedBook = this.recommendedBooks[0];

    filterByCategory(category: string) {
        this.selectedCategory = category;
        this.filteredBooks = category === 'All' ? this.books : this.books.filter(book => book.category === category);
    }

    selectBook(book: any) {
        this.selectedBook = book;
    }
}
