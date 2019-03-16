import React, {Component} from 'react';

class MovieItem extends Component {
  render() {
    const {item} = this.props;

    return (
        <div>
          <p>
            {item.title}
          </p>
        </div>
    );
  }
}

export default MovieItem;
