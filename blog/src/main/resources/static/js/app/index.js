var main = {
    init: function() {
        var _this = this;
        // 글 등록 버튼 이벤트
        $('#btn-save').on('click', function() {
            _this.save();
        });
        // 글 수정 버튼 이벤트
        $('#btn-update').on('click', function() {
            _this.update();
        });
        // 글 삭제 버튼 이벤트
        $('#btn-delete').on('click', function() {
            _this.delete();
        });
    },
    save: function() {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        })
    },
    update: function() {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        // 게시글ID
        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        })
    },
    delete: function() {
        // 게시글ID
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        })
    }
};

main.init();